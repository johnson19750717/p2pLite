package cn.mobiledaily.p2plite.service;

import cn.mobiledaily.p2plite.domain.IdCardBack;
import cn.mobiledaily.p2plite.domain.IdCardFront;

import java.util.List;

/**
 * Created by johnson on 14-10-6.
 */
public interface IdCardService {
    public IdCardFront save(IdCardFront idCardFront);

    public IdCardBack save(IdCardBack idCardBack);

    public List<IdCardFront> getIdCardFronts();

    public List<IdCardBack> getIdCardFBacks();

}
