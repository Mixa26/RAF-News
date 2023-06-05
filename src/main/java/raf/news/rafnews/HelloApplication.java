package raf.news.rafnews;


import javax.inject.Singleton;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import raf.news.rafnews.filters.AuthFilter;
import raf.news.rafnews.filters.CORSFilter;
import raf.news.rafnews.repositories.*;
import raf.news.rafnews.services.*;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig{
    public HelloApplication() {
        this.property("jersey.config.beanValidation.enableOutputValidationErrorEntity.server", true);
        //property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        AbstractBinder binder = new AbstractBinder() {
            protected void configure() {
                this.bind(MySqlUserRepository.class).to(UserRepository.class).in(Singleton.class);
                this.bind(MySqlCategoryRepository.class).to(CategoryRepository.class).in(Singleton.class);
                this.bind(MySqlNewsRepository.class).to(NewsRepository.class).in(Singleton.class);
                this.bind(MySqlTypeRepository.class).to(TypeRepository.class).in(Singleton.class);
                this.bind(MySqlCommentRepository.class).to(CommentRepository.class).in(Singleton.class);
                this.bind(MySqlTagRepository.class).to(TagRepository.class).in(Singleton.class);
                this.bind(MySqlNews_Tag_TableRepository.class).to(News_Tag_TableRepository.class).in(Singleton.class);

                this.bindAsContract(UserService.class);
                this.bindAsContract(CategoryService.class);
                this.bindAsContract(NewsService.class);
                this.bindAsContract(TypeService.class);
                this.bindAsContract(CommentService.class);
                this.bindAsContract(TagService.class);
                this.bindAsContract(News_Tag_TableService.class);
            }
        };
        this.register(AuthFilter.class);
        this.register(CORSFilter.class);
        this.register(binder);
        this.packages(new String[]{"raf.news.rafnews.resources"});
    }
}