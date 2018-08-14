package cn.edu.cup

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TomcatInstanceServiceSpec extends Specification {

    TomcatInstanceService tomcatInstanceService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new TomcatInstance(...).save(flush: true, failOnError: true)
        //new TomcatInstance(...).save(flush: true, failOnError: true)
        //TomcatInstance tomcatInstance = new TomcatInstance(...).save(flush: true, failOnError: true)
        //new TomcatInstance(...).save(flush: true, failOnError: true)
        //new TomcatInstance(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //tomcatInstance.id
    }

    void "test get"() {
        setupData()

        expect:
        tomcatInstanceService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<TomcatInstance> tomcatInstanceList = tomcatInstanceService.list(max: 2, offset: 2)

        then:
        tomcatInstanceList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        tomcatInstanceService.count() == 5
    }

    void "test delete"() {
        Long tomcatInstanceId = setupData()

        expect:
        tomcatInstanceService.count() == 5

        when:
        tomcatInstanceService.delete(tomcatInstanceId)
        sessionFactory.currentSession.flush()

        then:
        tomcatInstanceService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        TomcatInstance tomcatInstance = new TomcatInstance()
        tomcatInstanceService.save(tomcatInstance)

        then:
        tomcatInstance.id != null
    }
}
