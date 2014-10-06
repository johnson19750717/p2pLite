package cn.mobiledaily.p2plite.service;

import cn.mobiledaily.p2plite.domain.IdCardBack;
import cn.mobiledaily.p2plite.domain.IdCardFront;
import cn.mobiledaily.p2plite.repository.IdCardBackRepository;
import cn.mobiledaily.p2plite.repository.IdCardFrontRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by johnson on 14-10-6.
 */
@Service(value = "idCardService")
@Transactional
public class IdCardServiceImpl implements IdCardService {
    @Inject
    private IdCardFrontRepository idCardFrontRepository;
    @Inject
    private IdCardBackRepository idCardBackRepository;

    @Override
    public IdCardFront save(IdCardFront idCardFront) {
        return idCardFrontRepository.save(idCardFront);
    }

    @Override
    public IdCardBack save(IdCardBack idCardBack) {
        return idCardBackRepository.save(idCardBack);
    }

    @Override
    public List<IdCardFront> getIdCardFronts() {
        return idCardFrontRepository.findAll();
    }

    @Override
    public List<IdCardBack> getIdCardFBacks() {
        return idCardBackRepository.findAll();
    }
}
