package cn.krisez.sign.model.person_model;

import cn.krisez.sign.persenter.person_presenter.PersonListener;
import cn.krisez.sign.utils.SharedPreferenceUtil;

/**
 * Created by Krisez on 2018-02-03.
 */

public class PersonModel implements IPersonModel {
    @Override
    public void localStudent(PersonListener listener) {
        listener.success(SharedPreferenceUtil.getStudent());
    }
}
