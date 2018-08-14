package cn.edu.cup

class AppPrefix {
    
    String prefix
    String internetPrefix
    String alias
    
    static hasMany = [userApp: UserApp]
    
    static constraints = {
    }
    
    String toString() {
        return "${prefix}[${alias}]"
    }
}
