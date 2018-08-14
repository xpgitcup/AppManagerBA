package cn.edu.cup

class TomcatInstance {

    String tomcatPath
    String appPrefix

    static constraints = {
        tomcatPath(unique: true)
        appPrefix(nullable: false)
    }

    String toString() {
        return "${tomcatPath}/${appPrefix}"
    }
}
