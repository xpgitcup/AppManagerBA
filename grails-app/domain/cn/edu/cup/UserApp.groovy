package cn.edu.cup

class UserApp {
    
    String   appName
    String   description
    Date     date
    AppRole  appRole

    static belongsTo = [tomcatInstance: TomcatInstance]
    
    static constraints = {
        appName()
        description()
        date()
        appRole(nullable: true)
    }
    
    static mapping = {
        sort("date","desc")
        sort("appName")
    }
    
    String toString() {
        return "${appName}"
    }
}
