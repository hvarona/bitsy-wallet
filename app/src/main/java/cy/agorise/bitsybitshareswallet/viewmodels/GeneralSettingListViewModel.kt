package cy.agorise.bitsybitshareswallet.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import cy.agorise.bitsybitshareswallet.dao.CrystalDatabase
import cy.agorise.bitsybitshareswallet.models.GeneralSetting

class GeneralSettingListViewModel(application: Application) : AndroidViewModel(application) {

    val generalSettingList: LiveData<List<GeneralSetting>>
    private val db: CrystalDatabase

    init {
        this.db = CrystalDatabase.getAppDatabase(application.applicationContext)!!
        generalSettingList = this.db.generalSettingDao().getAll()
    }

    fun saveGeneralSetting(generalSetting: GeneralSetting) {
        this.db.generalSettingDao().insertGeneralSetting(generalSetting)
    }

    fun saveGeneralSettings(vararg generalSettings: GeneralSetting) {
        this.db.generalSettingDao().insertGeneralSettings(generalSettings)
    }

    fun getGeneralSettingByName(name: String): GeneralSetting {
        return this.db.generalSettingDao().getSettingByName(name)
    }

    fun getGeneralSettingLiveDataByName(name: String): LiveData<GeneralSetting> {
        return this.db.generalSettingDao().getByName(name)
    }

    fun deleteGeneralSettings(vararg generalSettings: GeneralSetting) {
        this.db.generalSettingDao().deleteGeneralSettings(generalSettings)
    }

    fun deleteGeneralSettingByName(name: String) {
        this.db.generalSettingDao().deleteByName(name)
    }

    //public void addGeneralSetting(String name, String value){
    //    this.db.generalSettingDao().addGeneralSetting(name,value);
    //}
}
