(ns mx.roads.forgotten.landing.components.db
  "FRMX landing page database component.

  For more information, see the module-level code comments in
  ``mx.roads.forgotten.landing.components``."
  (:require [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [taoensso.carmine :as  redis]))

(defrecord DBClient []
  component/Lifecycle

  (start [component]
    (log/info "Setting up FRMX landing page database client ...")
    (let [db-cfg (get-in component [:cfg :db])]
      (log/debug "Using config:" db-cfg)
      (let [conn {:pool {} :spec db-cfg}]
        (log/debug "Component keys:" (keys component))
        (log/debug "Successfully created FRMX landing page database connection:"
                   conn)
        (assoc component :conn conn))))

  (stop [component]
    (log/info "* Tearing down FRMX landing page database client ...")
    (log/debug "Component keys" (keys component))
    (if-let [conn (:conn component)]
      (log/debug "Using connection object:" conn))
    (assoc component :conn nil)))

(defn new-db-client []
  (->DBClient))
