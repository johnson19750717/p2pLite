package cn.mobiledaily.p2plite.domain;

import cn.mobiledaily.p2plite.common.App;
import com.ocr.idcard.IDCard;
import com.ocr.idcard.IdCardScan;

/**
 * Created by johnson on 14-10-6.
 */
public class IdCardRecognizer {
    static {
        IdCardScan.addOcrServerIP(App.getInstance().getOcrServerHost(), App.getInstance().getOcrServerPort());
    }

    public IdCardRecognizer() {
    }


    public IDCard recognize(String fileName) {
        return IdCardScan.ocr(fileName);
    }
}