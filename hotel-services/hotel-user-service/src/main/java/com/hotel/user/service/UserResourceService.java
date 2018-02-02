package com.hotel.user.service;

import com.hotel.service.BaseService;
import com.hotel.user.dao.UserResourceRepository;
import com.hotel.user.entity.Resource;
import com.hotel.user.entity.UserResource;
import com.hotel.user.entity.UserResource_;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserResourceService extends BaseService<UserResource, Long, UserResourceRepository> {

    /**
     * 根据用户ID查找对应的资源权限
     * @param userId 用户ID
     * @return 有用权限的资源
     */
    @Transactional(readOnly = true)
    public List<Resource> findByUserId(final Long userId) {
        List<UserResource> userResources = super.findAll(((root, query, cb) -> {
            root.fetch(UserResource_.resource, JoinType.INNER);
            return cb.equal(root.get(UserResource_.userId), userId);
        }));
        if(userResources != null) {
            return userResources.stream().map(UserResource::getResource).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Transactional
    public void grantAuth(Long userId, List<Long> resourceIdList) {
        repository.deleteByUserId(userId);
        resourceIdList.forEach(resourceId -> {
            UserResource userResource = new UserResource();
            userResource.setResourceId(resourceId);
            userResource.setUserId(userId);
            save(userResource);
        });
    }
}
