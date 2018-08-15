package cn.edu.cup

import grails.gorm.services.Service

@Service(AppRoles)
interface AppRolesService {

    AppRoles get(Serializable id)

    List<AppRoles> list(Map args)

    Long count()

    void delete(Serializable id)

    AppRoles save(AppRoles appRoles)

}