package com.example.backend.service;

import com.example.backend.domain.Userthumb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Sisyphus
* @description 针对表【userthumb】的数据库操作Service
* @createDate 2023-05-25 19:59:12
*/
public interface UserthumbService extends IService<Userthumb> {
    Userthumb getThumbById(Integer user_id, Integer post_id);

    int insertThumb(Userthumb userthumb);

    int deleteThumbById(Integer user_id, Integer post_id);
}
