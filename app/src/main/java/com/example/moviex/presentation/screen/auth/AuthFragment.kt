package com.example.moviex.presentation.screen.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.moviex.R
import com.example.moviex.databinding.FragmentAuthBinding
import com.example.moviex.presentation.base.ui.BaseFragment
import com.example.moviex.util.delegate.RouterDelegate
import com.example.moviex.util.delegate.viewBindings
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : BaseFragment(R.layout.fragment_auth) {

    private val binding by viewBindings(FragmentAuthBinding::bind)
    private val router by RouterDelegate()
    private val viewModel by viewModel<AuthViewModel>()

    lateinit var mGoogleSignInClient: GoogleSignInClient
    private val callbackManager = CallbackManager.Factory.create()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarColor(R.color.startColorGradient)
        setNavigationBarColor(R.color.endColorGradient)
        if (viewModel.isSignedIn) {
            router.authToHome()
        } else {
            observeLiveData()
            setupViews()
        }
    }

    private fun setupViews() {
        signUpFacebookLogin()
        binding.btnSignUp.setOnClickListener {
            viewModel.signUp(
                    binding.tietEmail.text.toString(),
                    binding.tietPassword.text.toString())
        }
        signInWithGoogle()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                val creds = FacebookAuthProvider.getCredential(result?.accessToken?.token.toString())
                Firebase.auth.signInWithCredential(creds)
                        .addOnSuccessListener { router.authToHome() }
                        .addOnFailureListener { Log.e(LOG_TAG, it.localizedMessage) }
            }

            override fun onCancel() {
                Log.i(LOG_TAG, "Cancel")
            }

            override fun onError(error: FacebookException?) {
                Log.e(LOG_TAG, "onError ${error?.localizedMessage}")
            }
        })

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUIRE_CODE) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                Firebase.auth.signInWithCredential(credential)
                        .addOnSuccessListener { router.authToHome() }
                        .addOnFailureListener { Log.e(LOG_TAG, it.localizedMessage) }
            }
        } catch (e: ApiException) {
            Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun signUpFacebookLogin() {
        binding.btnSingUpWithFacebook.apply {
            fragment = this@AuthFragment
            setPermissions(listOf(EMAIL_PERM))
        }
    }

    private fun signInWithGoogle() {
        binding.btnSingInWithGoogle.setOnClickListener {
            startActivityForResult(mGoogleSignInClient.signInIntent, REQUIRE_CODE)
        }
    }

    private fun observeLiveData() {
        viewModel.navigationEventsLiveData.observe(viewLifecycleOwner) {
            router.navigate(it)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val LOG_TAG: String = "KURWA"
        private const val REQUIRE_CODE: Int = 123
        private const val EMAIL_PERM: String = "email"
    }
}
