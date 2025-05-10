package org.example.strategy;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @description: 多key, 多服务管理器
 * @author: wruiiwang
 * @create: 2024/1/16 16:49
 **/
public abstract class BaseDynamicMultiManager<Key, BaseService> {
    @Autowired
    private List<BaseService> baseServices;

    private Map<Key, List<BaseService>> serviceMap;

    private final AtomicBoolean startImmediately = new AtomicBoolean(false);

    protected Key getKey(BaseService baseService) {
        throw new RuntimeException("BaseDynamicManager not override getKey");
    }

    protected List<Key> getKeys(BaseService baseService) {
        return Collections.singletonList(getKey(baseService));
    }

    @PostConstruct
    public void init() {
        if (startImmediately.getAndSet(true)) {
            return;
        }
        serviceMap = new HashMap<>();
        for (BaseService baseService : baseServices) {
            for (Key key : getKeys(baseService)) {
                serviceMap.compute(key, (k, v) -> {
                    if (CollectionUtils.isEmpty(v)) {
                        v = new ArrayList<>();
                    }
                    v.add(baseService);
                    return v;
                });
            }
        }
        baseServices = null;
    }

    public List<BaseService> getServices(Key key) {
        return serviceMap.getOrDefault(key, Collections.emptyList());
    }

    public Optional<BaseService> getService(Key key) {
        List<BaseService> services = getServices(key);
        return Optional.ofNullable(CollectionUtils.isEmpty(services) ? null : services.get(0));
    }

    public BaseService getServiceOrNull(Key key) {
        return getServiceOrDefault(key, null);
    }

    public BaseService getServiceOrDefault(Key key, BaseService defaultBaseService) {
        return getService(key).orElse(defaultBaseService);
    }
}