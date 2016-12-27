(ns mx.roads.forgotten.landing.components.mq
  "FRMX landing page messaging component.

  For more information, see the module-level code comments in
  `mx.roads.forgotten.landing.components.httpd`."
  (:require [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [clojurewerkz.machine-head.client :as mqtt]))

(defrecord MQClient []
  component/Lifecycle

  (start [component]
    (log/info "Setting up FRMX landing page messaging client ...")
    (let [mq-cfg (get-in component [:cfg :messaging])]
      (log/debug "Using config:" mq-cfg)
      (let [conn-id (mqtt/generate-id)
            conn (mqtt/connect (format "%s://%s:%s"
                                 (:scheme mq-cfg) (:host mq-cfg) (:port mq-cfg))
                               conn-id)]
        (log/debug "Component keys:" (keys component))
        (log/debug "Successfully created FRMX landing page messaging connection:" conn)
        (assoc component :mq conn))))

  (stop [component]
    (log/info "* Tearing down FRMX landing page messaging client ...")
    (log/debug "Component keys" (keys component))
    (if-let [conn (:mq component)]
      (do (log/debug "Using connection object:" conn)
          (mqtt/disconnect conn)))
    (assoc component :mq nil)))

(defn new-messaging []
  (->MQClient))
