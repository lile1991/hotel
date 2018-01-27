package com.hotel.user.service;

import com.hotel.service.BaseService;
import com.hotel.user.dao.UserRepository;
import com.hotel.user.dto.UserQueryDto;
import com.hotel.user.entity.User;
import com.hotel.user.entity.User_;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends BaseService<User, Long, UserRepository> {

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return repository.findByUsernameOrMobile(username, username);
    }

    @Transactional(readOnly = true)
    public Page<User> findAll(UserQueryDto userQueryDto) {
        return super.findAll(((root, query, cb) -> {
            String name = userQueryDto.getName();
            if(StringUtils.isNotBlank(name)) {
                cb.equal(root.get(User_.name), userQueryDto.getName());
            }

            String mobile = userQueryDto.getMobile();
            if(StringUtils.isNotBlank(mobile)) {
                cb.equal(root.get(User_.mobile), userQueryDto.getMobile());
            }

            return null;
        }), userQueryDto.toPageable());
    }

    @Transactional
    public int updateLockState(Long id, boolean locked) {
        return repository.updateLockState(id, locked);
    }

}
