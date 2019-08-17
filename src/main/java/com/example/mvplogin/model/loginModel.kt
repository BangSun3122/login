package  com.example.mvplogin.model

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.mvplogin.contract.loginContract
import com.example.mvplogin.model.imodel.ILoginModel
import com.example.mvplogin.model.imodel.OnLoginListener
import com.example.mvplogin.model.imodel.basemodel
import kotlinx.android.synthetic.main.testlogin.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class loginModel : basemodel(), loginContract.IModel{

}

