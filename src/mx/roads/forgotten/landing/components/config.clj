(ns mx.roads.forgotten.landing.components.config
  "FRMX landing page configuration component.

  For more information, see the module-level code comments in
  ``mx.roads.forgotten.landing.components``."
  (:require [clojure.tools.logging :as log]
            [clojure-ini.core :as ini]
            [com.stuartsierra.component :as component]
            [mx.roads.forgotten.landing.const :as const]
            [mx.roads.forgotten.landing.util :as util]))

(defrecord Configuration [cfg]
  component/Lifecycle

  (start [component]
    (log/info "Setting up FRMX landing page configuration component ...")
    (log/debug "Configuration data:" cfg)
    (merge component cfg))

  (stop [component]
    (log/info "* Tearing down FRMX landing page configuration component ...")))

(defn new-configuration []
  (log/debug "Building configuration component ...")
  (-> (util/get-env-config)
      (or const/file-config)
      (util/expand-user-home)
      (ini/read-ini :keywordize? true)
      (->Configuration)))
