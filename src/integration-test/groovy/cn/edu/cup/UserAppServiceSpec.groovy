package cn.edu.cup

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UserAppServiceSpec extends Specification {

    UserAppService userAppService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new UserApp(...).save(flush: true, failOnError: true)
        //new UserApp(...).save(flush: true, failOnError: true)
        //UserApp userApp = new UserApp(...).save(flush: true, failOnError: true)
        //new UserApp(...).save(flush: true, failOnError: true)
        //new UserApp(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //userApp.id
    }

    void "test get"() {
        setupData()

        expect:
        userAppService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<UserApp> userAppList = userAppService.list(max: 2, offset: 2)

        then:
        userAppList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        userAppService.count() == 5
    }

    void "test delete"() {
        Long userAppId = setupData()

        expect:
        userAppService.count() == 5

        when:
        userAppService.delete(userAppId)
        sessionFactory.currentSession.flush()

        then:
        userAppService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        UserApp userApp = new UserApp()
        userAppService.save(userApp)

        then:
        userApp.id != null
    }
}
