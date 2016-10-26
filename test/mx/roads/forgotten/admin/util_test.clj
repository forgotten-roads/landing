(ns mx.roads.forgotten.admin.util-test
  (:require [clojure.test :refer :all]
            [mx.roads.forgotten.admin.util :as util])
  (:import [java.net.URI]))

(deftest ^:unit uri
  (let [uri (util/new-url "scheme://1.2.3.4:5678/path")]
    (is (=  (type uri) java.net.URI))
    (is (=  (util/get-scheme uri) "scheme"))
    (is (=  (util/get-host uri) "1.2.3.4"))
    (is (=  (util/get-port uri) 5678))
    (is (=  (util/get-path uri) "/path"))))

(deftest ^:unit get-env-config
  (is (= (util/get-env-config) (or "" nil)))
  (is (= (do
           (util/set-env-config "config-file.ini")
           (let [filename (util/get-env-config)]
             (util/set-env-config "")
             filename))
         "config-file.ini")))
