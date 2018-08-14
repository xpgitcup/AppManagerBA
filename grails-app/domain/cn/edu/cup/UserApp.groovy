package cn.edu.cup

class UserApp {
    
    String   appPath
    String   appName
    Date     date
    AppRoles appRoles

    static belongsTo = [appPrefix: AppPrefix]
    
    static constraints = {
        appName()
        appPath()
        appPrefix(nullable: true)
        date()
        appRoles(nullable: true)
    }
    
    static mapping = {
        sort("date","desc")
        sort("appName")
    }
    
    String toString() {
        return "${appName}"
    }
}
