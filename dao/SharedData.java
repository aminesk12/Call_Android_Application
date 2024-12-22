package esisa.ac.ma.dao;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import esisa.ac.ma.entities.SMS;

public class SharedData extends ViewModel {
    private MutableLiveData<SMS> data=new MutableLiveData<>();

    public MutableLiveData<SMS> getData() {
        return data;
    }

    public void setData(SMS smsdata) {
        this.data.setValue(smsdata);
    }
}