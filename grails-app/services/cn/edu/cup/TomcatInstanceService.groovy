package cn.edu.cup

import grails.gorm.services.Service

@Service(TomcatInstance)
interface TomcatInstanceService {

    TomcatInstance get(Serializable id)

    List<TomcatInstance> list(Map args)

    Long count()

    void delete(Serializable id)

    TomcatInstance save(TomcatInstance tomcatInstance)

}