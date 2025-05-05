package com.bloxbean.example;

import com.bloxbean.cardano.yaci.core.common.Constants;
import com.bloxbean.cardano.yaci.store.client.staking.DummyStakingClient;
import com.bloxbean.cardano.yaci.store.client.staking.StakingClient;
import com.bloxbean.cardano.yaci.store.common.config.StoreProperties;
import com.bloxbean.cardano.yaci.store.utxo.UtxoStoreProperties;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class StoreConfiguration {
    private static final Logger log = LoggerFactory.getLogger(StoreConfiguration.class);

    private String cardanoHost = Constants.PREPROD_PUBLIC_RELAY_ADDR;
    private int cardanoPort = Constants.PREPROD_PUBLIC_RELAY_PORT;
    private long protocolMagic = Constants.PREPROD_PROTOCOL_MAGIC;

    static {
        System.setProperty("org.jooq.no-logo", "true");
        System.setProperty("org.jooq.no-tips", "true");
    }

    @Produces
    public StoreProperties storeProperties() {
        StoreProperties storeProperties = new StoreProperties();
        storeProperties.setCardanoHost(cardanoHost);
        storeProperties.setCardanoPort(cardanoPort);
        storeProperties.setEventPublisherId(1L);
        storeProperties.setProtocolMagic(protocolMagic);
        storeProperties.setCursorCleanupInterval(60);
        storeProperties.setCursorNoOfBlocksToKeep(1000);
        return storeProperties;
    }

    @Produces
    public UtxoStoreProperties utxoStoreProperties() {
        var utxoStoreProperties = new UtxoStoreProperties();
        return utxoStoreProperties;
    }

    @Produces
    public StakingClient stakingClient() {
        return new DummyStakingClient();
    }

    @Produces
    public Configuration jooqConfiguration(DSLContext dslContext) {
        return new DefaultConfiguration();
    }
}
