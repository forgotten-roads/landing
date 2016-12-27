(ns mx.roads.forgotten.landing.components.system
  "Top-level FRMX landing page top-level component.

  For more information, see the module-level code comments in
  ``mx.roads.forgotten.landing.components``."
  (:require [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]))

(defrecord FRMXSystem []
  component/Lifecycle

  (start [component]
    (log/info (str "FRMX landing page system dependencies started; "
                   "finishing FRMX landing page startup ..."))
    ;; XXX add any start-up needed for system as a whole
    (log/debug "FRMX landing page startup complete.")
    component)

  (stop [component]
    (log/info "Shutting down top-level FRMX landing page:")
    ;; XXX add any tear-down needed for system as a whole
    (log/debug (str "Top-level shutdown complete; shutting down system "
                    "dependencies ..."))
    component))

(defn new-toplevel []
  (->FRMXSystem))
