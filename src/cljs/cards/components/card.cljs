(ns cards.components.card
  (:require
    (reagent.core :as r)))

(defrecord Card [type value header-content body-content update-fn])

(defmulti make-card :type)

(defmethod make-card :twitter-user [c]
  (-> c
    (assoc :header-content (:value c)
           :body-content ""
           :update-fn (fn [this]
                        (update this :body-content #(str % "."))))
    (map->Card)))

(defn card [c]
  (let [c (r/atom (make-card c))]
    (fn []
      (js/setTimeout #(swap! c (:update-fn @c)) 1000)
      [:div.card.card-shadow.text-dark
       [:div.card-header
        [:h5.pt-2 (:header-content @c)]]
       [:div.card-block
        [:p.card-text (:body-content @c)]]])))
