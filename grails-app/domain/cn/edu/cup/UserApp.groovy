package cn.edu.cup

class UserApp {
    
    String   appPath
    String   appName
    Date     date
    AppRoles appRoles

    static belongsTo = [tomcatInstance: TomcatInstance]
    
    static constraints = {
        appName()
        appPath()
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
