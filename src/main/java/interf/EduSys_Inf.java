/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interf;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface EduSys_Inf <N> {
    
    public List<N> getAllData();
    public int insert(N n);
    public int delete(String ma);
    public int update(N n,String ma);
    public N getTheoMa(String ma);
    
}
