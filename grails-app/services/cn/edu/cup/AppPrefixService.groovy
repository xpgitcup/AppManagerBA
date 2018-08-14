package cn.edu.cup

import grails.gorm.services.Service

@Service(AppPrefix)
interface AppPrefixService {

    AppPrefix get(Serializable id)

    List<AppPrefix> list(Map args)

    Long count()

    void delete(Serializable id)

    AppPrefix save(AppPrefix appPrefix)

}