package cn.edu.cup

class AppRoles {
    
    String name
    Integer appRight
    
    static constraints = {
    }
    
    String toString() {
        return "${name}(${appRight})"
    }
}
