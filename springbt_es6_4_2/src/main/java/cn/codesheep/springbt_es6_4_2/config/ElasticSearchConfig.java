package cn.codesheep.springbt_es6_4_2.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ElasticSearchConfig {

    @Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.tcpport}")
    private int esTcpPort;

    @Value("${elasticsearch.httpport}")
    private int esHttpPort;

    @Value("${elasticsearch.cluster.name}")
    private String esClusterName;

    @Bean
    public TransportClient esTransportClient() throws UnknownHostException {

        Settings settings = Settings.builder()
                .put( "cluster.name", this.esClusterName )
                .put("client.transport.sniff", true)
                .build();
        TransportAddress master = new TransportAddress( InetAddress.getByName( esHost ), esTcpPort );
		TransportClient esClient = new PreBuiltTransportClient(settings).addTransportAddress( master );
        return esClient;
    }

    @Bean
    public RestHighLevelClient esRestHighLevelClient() {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost( esHost, esHttpPort, "http" )
                )
        );
        return client;
    }
}
