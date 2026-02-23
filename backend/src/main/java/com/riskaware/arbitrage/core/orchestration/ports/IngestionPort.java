package com.riskaware.arbitrage.core.orchestration.ports;

/**
 * Toodab torujuhtme (pipeline) jaoks
 * toor-turu / hinnainfo hetkeseisu (snapshot).
 *
 * @param <TPriceSnapshot> hinnainfo andmetüübi abstraktsioon
 */
public interface IngestionPort<TPriceSnapshot> {

    /**
     * Toob ja tagastab hetke turu/hinna snapshot’i,
     * mida kasutatakse edasises töötlusahelas.
     */
    TPriceSnapshot fetchSnapshot();
}