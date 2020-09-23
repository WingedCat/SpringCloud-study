package xpu.edu.hcp.springcloud.service.impl;

import org.springframework.stereotype.Service;
import xpu.edu.hcp.springcloud.dao.StorageDao;
import xpu.edu.hcp.springcloud.service.StorageService;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("storageService")
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Resource
    private StorageDao storageDao;

    // 扣减库存
    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("------->storage-service中扣减库存开始");
        storageDao.decrease(productId,count);
        LOGGER.info("------->storage-service中扣减库存结束");
    }
}
