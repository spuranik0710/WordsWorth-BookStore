/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.ejb;

import edu.iit.sat.itmd4515.spuranik.domain.security.Group;
import edu.iit.sat.itmd4515.spuranik.domain.security.User;
import java.util.List;
import javax.ejb.Stateless;

/**
 *UserService Service extending Base Service consists of method for named query
 * Along with createnewuser method to create new user
 * When user clicks on Sign up Customer is added to the customers group
 * @author Pooja
 */
@Stateless
public class UserService extends BaseService<User> {
//    private EntityManager em;

    public UserService() {
        super(User.class);
    }

    @Override
    public List<User> findAll() {
        return getEntityManager().createNamedQuery("User.findAll", User.class).getResultList();
    }

    
    public void createnewuser(User user) {
        List<Group> groups = getEntityManager().createNamedQuery("Group.findAll", Group.class).getResultList();

        for (Group group : groups) {
            if (group.getGroupName().equals("CUSTOMERS")) {
                user.addGroup(group);
                user.setEnabled(false);
                getEntityManager().persist(user);
            }
        }
    }

}
