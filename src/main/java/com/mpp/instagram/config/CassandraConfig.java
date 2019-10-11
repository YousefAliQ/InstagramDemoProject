package com.mpp.instagram.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.*;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

import java.util.Arrays;
import java.util.List;


/**
 * Created by jt on 10/6/17.
 */
//@Configuration
//public class CassandraConfig extends AbstractCassandraConfiguration {
//
//    public static final String KEYSPACE = "guru_keyspace";
//
//    @Override
//    public SchemaAction getSchemaAction() {
//        return SchemaAction.CREATE_IF_NOT_EXISTS;
//    }
//
//    @Override
//    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
//        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(KEYSPACE);
//
//        return Arrays.asList(specification);
//    }
//
//    @Override
//    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
//        return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(KEYSPACE));
//    }
//
//    @Override
//    protected String getKeyspaceName() {
//        return KEYSPACE;
//    }
//
//    @Override
//    public String[] getEntityBasePackages() {
//        return new String[]{"guru.springframework.domain"};
//    }
//}

@Configuration
@EnableReactiveCassandraRepositories(basePackages = "com.mpp.instagram")
public class CassandraConfig extends AbstractCassandraConfiguration {
    private static final String KEYSPACE = "instagram2";
    private static final String NODES = "127.0.0.1"; // comma seperated nodes


    @Bean
    @Override
    public CassandraCqlClusterFactoryBean cluster() {
        CassandraCqlClusterFactoryBean bean = new CassandraCqlClusterFactoryBean();
        bean.setKeyspaceCreations(getKeyspaceCreations());
        bean.setContactPoints(NODES);
        return bean;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected String getKeyspaceName() {
        return KEYSPACE;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"com.mpp.instagram"};
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {

        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(KEYSPACE)
                .ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES, true);

        return Arrays.asList(specification);
    }
    @Override
    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
        return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(KEYSPACE));
    }

}