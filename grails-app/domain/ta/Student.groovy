package ta

import org.grails.datastore.mapping.query.Query

class Student {
    String name;
    String login;
    //List<Criterion> criterions;
    static constraints = {
        name blank : false
        login unique : true, blank:false;
    }
}
