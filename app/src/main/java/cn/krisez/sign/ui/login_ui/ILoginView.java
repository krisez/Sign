package cn.krisez.sign.ui.login_ui;

/**
 * Created by Krisez on 2018-01-27.
 */

public interface ILoginView {
    String getXh();
    String getMM();
    void showError(String s);
    void showProgress();
    void dismissProgress();
}
