package com.example.demoprojectandroid.SocialLogin

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.demoprojectandroid.R
import com.example.demoprojectandroid.databinding.ActivitySocialLoginBinding
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase
import org.json.JSONException
import org.json.JSONObject
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Arrays
import java.util.Locale


class SocialLoginActivity : AppCompatActivity() {
    //google login
    lateinit var mGoogleSignInClient: GoogleSignInClient

    //    var googleLoginActivityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
    //    ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
    //        if (result.resultCode == Activity.RESULT_OK) {
    //            //  you will get result here in result.data
    //        }
    //    }


    private var mAuth: FirebaseAuth? = null


    private lateinit var callbackManager: CallbackManager
    lateinit var binding: ActivitySocialLoginBinding
    private lateinit var mGoogleApiClient: GoogleApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySocialLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FacebookSdk.sdkInitialize(getApplicationContext());
        mAuth = FirebaseAuth.getInstance()
        callbackManager = CallbackManager.Factory.create()
        printHashKey(this);



        binding.loginButton.setOnClickListener(View.OnClickListener {

            prepareFacebook()
        })

        binding.googleSignin.setOnClickListener(View.OnClickListener {

            signInWithGoogle()
            signIn()
        })

        binding.etEmail.text = "Logout"
        binding.etEmail.setOnClickListener(View.OnClickListener {
            mAuth!!.signOut()
        })

    }


    private fun prepareFacebook() {
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));


        // Register callback
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {

                Log.e("TAG", "onSuccess: " + loginResult.toString())
                val accessToken = loginResult.accessToken
                getUserProfile(accessToken)
            }

            override fun onCancel() {
                // Handle canceled login
            }

            override fun onError(exception: FacebookException) {
                // Handle error
            }
        })

    }


    // Function to retrieve user profile information using Graph API
    private fun getUserProfile(accessToken: AccessToken) {
        val graphRequest = GraphRequest.newMeRequest(accessToken) { _, response ->
            try {
                val jsonObject = response?.jsonObject
                val userId = jsonObject?.getString("id")
                val userName = jsonObject?.getString("name")
                val userEmail = jsonObject?.getString("email")

                Toast.makeText(this, userEmail, Toast.LENGTH_SHORT).show()
                Log.e(TAG, "getUserProfile: " + userId)
                Log.e(TAG, "getUserProfile: " + userEmail)
                Log.e(TAG, "getUserProfile: " + userName)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        val parameters = Bundle()
        parameters.putString("fields", "id,name,email")
        graphRequest.parameters = parameters


        graphRequest.executeAsync()
    }

    fun printHashKey(context: Context) {
        try {
            val info: PackageInfo = context.packageManager.getPackageInfo("com.example.demoprojectandroid", PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                var md: MessageDigest? = null
                try {
                    md = MessageDigest.getInstance("SHA")
                } catch (e: NoSuchAlgorithmException) {
                    e.printStackTrace()
                }
                md?.update(signature.toByteArray())
                md?.let {
                    Log.e("KeyHash:", Base64.encodeToString(it.digest(), Base64.DEFAULT))
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e("hashkey_error", e.toString())
        }
    }


    /*****************Firebase google login *********************/


    private fun signInWithGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }


    // this part was missing thanks to wesely


    private fun handleSignInResult(result: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(result.idToken, null)
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(this) { task: Task<AuthResult?> ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Google login success" + result.displayName, Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "handleSignInResult: Login Success " + result.displayName)

                } else {
                    Log.e(TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }


    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // The user is signed in, you can access user information here
            Log.d("GoogleSignInFirebase", "User ID: ${user.uid}")
            Log.d("GoogleSignInFirebase", "Email: ${user.email}")
            // TODO: Handle the signed-in user, for example, update UI or navigate to another activity
        } else {
            // The user is not signed in, handle this case
            Log.d("GoogleSignInFirebase", "User not signed in.")
        }
    }


    private fun signIn() {

        val signInIntent = mGoogleSignInClient.signInIntent
        googleLoginActivityResult.launch(signInIntent)
    }

    private var googleLoginActivityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            // Handle the result here using result.data
            val data: Intent? = result.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.e(TAG, account.email.toString())
                handleSignInResult(account)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI accordingly
                Log.w("GoogleSignInFirebase", "Google sign in failed", e)
                // TODO: Handle sign-in failure
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)


    }


}