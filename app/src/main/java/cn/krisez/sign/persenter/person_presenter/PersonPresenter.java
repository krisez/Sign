package cn.krisez.sign.persenter.person_presenter;

import cn.krisez.sign.bean.Students;
import cn.krisez.sign.bean.Teacher;
import cn.krisez.sign.model.person_model.IPersonModel;
import cn.krisez.sign.model.person_model.PersonModel;
import cn.krisez.sign.ui.person_ui.IPersonView;

/**
 * Created by Krisez on 2018-02-03.
 */

public class PersonPresenter implements IPersonPresenter,PersonListener {

    private IPersonView mView;
    private IPersonModel mIPersonModel;

    public PersonPresenter(IPersonView view) {
        mView = view;
        mIPersonModel = new PersonModel();
    }
    @Override
    public void local() {
        mView.showPro();
        mIPersonModel.local(this);
    }

    @Override
    public void success(Students students) {
        mView.dismiss();
        mView.setInfo(students);
    }

    @Override
    public void success(Teacher teacher) {
        mView.dismiss();
        mView.setInfo(teacher);
    }

    @Override
    public void failed(String s) {

    }



}
