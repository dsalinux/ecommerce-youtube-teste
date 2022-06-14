package br.com.professordanilo.ecommerceyouteste.interceptors;

import br.com.professordanilo.ecommerceyouteste.interceptors.annotation.Transacao;
import br.com.professordanilo.ecommerceyouteste.util.exception.ErroSistemaException;
import java.io.Serializable;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

/**
 *
 * @author danilo
 */
@Interceptor
@Transacao
public class TransacaoInterceptor implements Serializable {
    
    @Inject
    private EntityManager em;
    
    @AroundInvoke
    public Object gerenciarTransacao(InvocationContext context) throws Exception{
        em.getTransaction().begin();
        try {
            Object retorno = context.proceed();
            em.getTransaction().commit();
            return retorno;
        } catch (RollbackException ex) {
            throw new ErroSistemaException("Erro ao confirmar as modificações no banco de dados.", ex);
        } catch (IllegalStateException ex){
            em.getTransaction().rollback();
            throw new ErroSistemaException("Erro ao confirmar as modificações no banco de dados.", ex);
        } catch(Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }
    
}
