package com.bloxbean.example;

import com.bloxbean.cardano.yaci.helper.BlockRangeSync;
import com.bloxbean.cardano.yaci.helper.GenesisBlockFinder;
import com.bloxbean.cardano.yaci.helper.TipFinder;
import com.bloxbean.cardano.yaci.store.common.executor.ParallelExecutor;
import com.bloxbean.cardano.yaci.store.core.service.BlockFetchService;
import com.bloxbean.cardano.yaci.store.core.service.StartService;
import com.bloxbean.cardano.yaci.store.core.service.publisher.ByronBlockEventPublisher;
import com.bloxbean.cardano.yaci.store.core.service.publisher.ShelleyBlockEventPublisher;
import com.bloxbean.cardano.yaci.store.core.storage.api.CursorStorage;
import com.bloxbean.cardano.yaci.store.core.storage.api.EraStorage;
import com.bloxbean.cardano.yaci.store.events.api.DomainEventPublisher;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.concurrent.ExecutorService;

@ApplicationScoped
public class NodeServerInitializer {

    @Inject
    private DomainEventPublisher publisher;

    @Inject
    @Named("blockExecutor")
    ExecutorService blockExecutor;

    @Inject
    EraStorage eraStorage;

    @Inject
    CursorStorage cursorStorage;

    @Inject
    TipFinder tipFinder;

    @Inject
    BlockRangeSync blockRangeSync;

    @Inject
    GenesisBlockFinder genesisBlockFinder;

    @Inject
    ParallelExecutor parallelExecutor;

    @Inject
    ShelleyBlockEventPublisher shelleyBlockEventPublisher;

    @Inject
    ByronBlockEventPublisher byronBlockEventPublisher;

    @Inject
    BlockFetchService blockFetchService;

    @Inject
    StartService startService;

  public void onStart(@Observes StartupEvent ev) {
    System.out.println("App is starting, starting Node server...");

      publisher.publishEvent("hello");
      System.out.println(blockExecutor);
      System.out.println(cursorStorage);
      System.out.println(tipFinder.find().block());
      System.out.println(genesisBlockFinder.getGenesisAndFirstBlock());
      System.out.println(parallelExecutor);

      startService.start();
  }
}
