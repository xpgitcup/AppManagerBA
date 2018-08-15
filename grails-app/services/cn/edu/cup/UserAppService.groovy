package cn.edu.cup

import grails.gorm.services.Service

@Service(UserApp)
interface UserAppService {

    UserApp get(Serializable id)

    List<UserApp> list(Map args)

    Long count()

    void delete(Serializable id)

    UserApp save(UserApp userApp)

}