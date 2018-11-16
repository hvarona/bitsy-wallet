package cy.agorise.bitsybitshareswallet.activities

import android.accounts.Account
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import cy.agorise.bitsybitshareswallet.R
import cy.agorise.bitsybitshareswallet.dao.BitsyDatabase
import cy.agorise.bitsybitshareswallet.models.AccountSeed
import cy.agorise.bitsybitshareswallet.models.CryptoCurrency
import cy.agorise.bitsybitshareswallet.models.CryptoNetAccount
import cy.agorise.bitsybitshareswallet.repository.Repository
import cy.agorise.bitsybitshareswallet.repository.RepositoryManager
import cy.agorise.bitsybitshareswallet.utils.Constants
import kotlinx.android.synthetic.main.activity_license.*
import java.util.*

class LicenseActivity : CustomActivity(){

    internal lateinit var db: BitsyDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_license)

        var cryptoNetAccount: CryptoNetAccount? = null
        if(RepositoryManager.getAccountsRepository(this).getTotalCryptoNetAccounts()>0){
            cryptoNetAccount = RepositoryManager.getAccountsRepository(this).getCryptoNetLocalAcount()!!
        }

        if (PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean(Constants.KEY_LICENCE_AGREED, false)) {

            if(cryptoNetAccount != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                val intent = Intent(this, AccountActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        else{

            /*
            * Load the licence only if it is necesarry
            * */
            val html = getString(R.string.licence_html)
            wvEULA.loadData(html, "text/html", "UTF-8")
        }

        btnAgree.setOnClickListener(){

            PreferenceManager.getDefaultSharedPreferences(baseContext).edit()
                .putBoolean(Constants.KEY_LICENCE_AGREED, true).apply()

            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnDisAgree.setOnClickListener(){
            System.exit(0)
        }
    }
}