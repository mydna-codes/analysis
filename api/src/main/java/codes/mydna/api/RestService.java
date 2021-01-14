package codes.mydna.api;

import codes.mydna.auth.common.RealmRole;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("v1")
@DeclareRoles({RealmRole.ADMIN, RealmRole.USER, RealmRole.PRO_USER})
public class RestService extends Application {

}
