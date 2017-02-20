package com.booking.repository.search;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import java.util.UUID;

import org.elasticsearch.client.node.NodeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

public class CreateInMemoryElasticSearch {

	@Bean(name = "elasticsearchTemplateHotelRepo")
	public ElasticsearchOperations elasticsearchTemplateVariable() {
		try {
			return new ElasticsearchTemplate(getNodeLocalClient());
		} catch (Exception e) {
			throw new RuntimeException("Exception while creating client for elastic search variable , msg :"
					+ e.getMessage());
		}
	}

	@Bean
	public NodeClient getNodeLocalClient() {
		return (NodeClient) nodeBuilder().clusterName(UUID.randomUUID().toString()).local(true).node().client();
	}
}
