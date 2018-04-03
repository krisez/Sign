package cn.krisez.sign.model.table_model;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import cn.krisez.sign.App;
import cn.krisez.sign.bean.KeBiao;
import cn.krisez.sign.persenter.table_presenter.TableListener;
import cn.krisez.sign.utils.SharedPreferenceUtil;
import cn.krisez.sign.utils.Utils;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Krisez on 2018-01-27.
 */

public class TableModel implements ITableModel {

    @Override
    public void getTableData(final String xh, final TableListener listener) {
        String url = "";
        String params = "";
        String i = App.getUser().getType();
        if (i.equals("1")) {
            url = App.stu_kb;
            params = "xh";
        } else {
            url = App.tea_kb;
            params = "teaId";
        }
        OkHttpUtils.get().addParams(params, xh).url(url).build().connTimeOut(3000).readTimeOut(5000).execute(new Callback() {
            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                String data = response.body().string();
                SharedPreferenceUtil.setTable(xh, data);
                return dealTable(data);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                listener.failed(e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {
                listener.success((List<KeBiao>) response);
            }
        });
    }

    //数据处理
    @Override
    public List<KeBiao> dealTable(String tableData) {
        List<KeBiao> keBiaos = new ArrayList<>();
        Document doc = Jsoup.parse(tableData);
        Elements es = doc.getElementsByClass("printTable");
        Elements ele = es.select("td");
        for (int i = 8; i < 72; i++) {
            Element e = ele.get(i);
            String content = e.text();
            if (content.length() > 20) {
                keBiaos.add(new KeBiao(" ", Utils.newString(content)));
            } else if (content.length() < 20) {
                keBiaos.add(new KeBiao(" ", content));
            }
        }
        return keBiaos;
    }
}
