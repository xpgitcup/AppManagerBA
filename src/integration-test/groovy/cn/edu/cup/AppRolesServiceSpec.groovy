package cn.edu.cup

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AppRolesServiceSpec extends Specification {

    AppRolesService appRolesService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new AppRoles(...).save(flush: true, failOnError: true)
        //new AppRoles(...).save(flush: true, failOnError: true)
        //AppRoles appRoles = new AppRoles(...).save(flush: true, failOnError: true)
        //new AppRoles(...).save(flush: true, failOnError: true)
        //new AppRoles(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //appRoles.id
    }

    void "test get"() {
        setupData()

        expect:
        appRolesService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<AppRoles> appRolesList = appRolesService.list(max: 2, offset: 2)

        then:
        appRolesList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        appRolesService.count() == 5
    }

    void "test delete"() {
        Long appRolesId = setupData()

        expect:
        appRolesService.count() == 5

        when:
        appRolesService.delete(appRolesId)
        sessionFactory.currentSession.flush()

        then:
        appRolesService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        AppRoles appRoles = new AppRoles()
        appRolesService.save(appRoles)

        then:
        appRoles.id != null
    }
}
