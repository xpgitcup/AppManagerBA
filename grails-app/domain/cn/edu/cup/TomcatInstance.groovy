package cn.edu.cup

class TomcatInstance {

    String tomcatPath
    String wanIP
    String lanIP
    Integer port = 8080

    static hasMany = [userApp: UserApp]

    static constraints = {
        tomcatPath(unique: true)
        wanIP()
        lanIP()
        port()
    }

    String toString() {
        return "${tomcatPath}"
    }
}
