(ns mx.roads.forgotten.landing.components
  "FRMX landing page components

  For more information on the Clojure component library, see:

   * https://github.com/stuartsierra/component
   * https://www.youtube.com/watch?v=13cmHf_kt-Q"
  (:require [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [mx.roads.forgotten.landing.components.config :as config]
            [mx.roads.forgotten.landing.components.db :as db]
            [mx.roads.forgotten.landing.components.httpd :as httpd]
            [mx.roads.forgotten.landing.components.logger :as logger]
            [mx.roads.forgotten.landing.components.mq :as mq]
            [mx.roads.forgotten.landing.components.system :as system]))

(defn init [app]
  (component/system-map
    :cfg (config/new-configuration)
    :db (component/using
          (db/new-db-client)
          [:cfg
           :logger])
    :httpd (component/using
             (httpd/new-server app)
             [:cfg
              :logger])
    :logger (component/using
              (logger/new-logger)
              [:cfg])
    :mq (component/using
          (mq/new-messaging)
          [:cfg
           :logger])
    :app (component/using
           (system/new-toplevel)
           [:db
            :httpd
            :mq])))

(defn stop [system component-key]
  (->> system
       (component-key)
       (component/stop)
       (assoc system component-key)))

(defn start [system component-key]
  (->> system
       (component-key)
       (component/start)
       (assoc system component-key)))

(defn restart [system component-key]
  (-> system
      (stop component-key)
      (start component-key)))
