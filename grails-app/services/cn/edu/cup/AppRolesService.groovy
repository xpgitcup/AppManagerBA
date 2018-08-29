package cn.edu.cup

import grails.gorm.services.Service

@Service(AppRole)
interface AppRolesService {

    AppRole get(Serializable id)

    List<AppRole> list(Map args)

    Long count()

    void delete(Serializable id)

    AppRole save(AppRole appRoles)

}