package cn.mobiledaily.p2plite.web;

import cn.mobiledaily.p2plite.common.App;
import cn.mobiledaily.p2plite.domain.IdCardBack;
import cn.mobiledaily.p2plite.domain.IdCardFront;
import cn.mobiledaily.p2plite.domain.IdCardRecognizer;
import cn.mobiledaily.p2plite.service.IdCardService;
import com.ocr.idcard.IDCard;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by johnson on 14-10-6.
 */
@Controller
@RequestMapping("/idcard")
public class IdCardController {
    private static final int FILENAME_LENGTH = 12;
    private static final String FILENAME_PATTERN = "%s/%s.jpg";
    @Inject
    private IdCardService idCardService;

    @RequestMapping(value = "/front", method = RequestMethod.POST)
    @ResponseBody
    public Callable<IdCardFront> uploadIdCardFront(@RequestParam("file") final MultipartFile file) throws IOException {
        if (file.isEmpty())
            throw new IllegalArgumentException("error.ocr.idCard.front.bad.argument.empty");
        return new Callable<IdCardFront>() {
            public IdCardFront call() throws Exception {
                String fileName = saveFile(file);
                IDCard idCard = new IdCardRecognizer().recognize(fileName);
                return idCardService.save(new IdCardFront(idCard.Name, idCard.CardNo, idCard.Sex, idCard.Folk, idCard.Birthday, idCard.Address, fileName));
            }
        };
    }

    @RequestMapping(value = "/back", method = RequestMethod.POST)
    @ResponseBody
    public Callable<IdCardBack> uploadIdCardBack(@RequestParam("file") final MultipartFile file) throws IOException {
        if (file.isEmpty())
            throw new IllegalArgumentException("error.ocr.idCard.back.bad.argument.empty");
        return new Callable<IdCardBack>() {
            public IdCardBack call() throws Exception {
                String fileName = saveFile(file);
                IDCard idCard = new IdCardRecognizer().recognize(fileName);
                return idCardService.save(new IdCardBack(idCard.IssueAuthority, idCard.ValidPeriod, fileName));
            }
        };
    }

    private String saveFile(MultipartFile file) {
        String randomFileName = RandomStringUtils.randomAlphanumeric(FILENAME_LENGTH);
        String fileName = String.format(FILENAME_PATTERN, App.getInstance().getImageStore(), randomFileName);
        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileName));
            stream.write(file.getBytes());
            stream.close();
            return randomFileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/front", method = RequestMethod.GET)
    @ResponseBody
    public List<IdCardFront> getIdCardFronts() {
        return idCardService.getIdCardFronts();
    }

    @RequestMapping(value = "/back", method = RequestMethod.GET)
    @ResponseBody
    public List<IdCardBack> getIdCardBacks() {
        return idCardService.getIdCardFBacks();
    }
}
