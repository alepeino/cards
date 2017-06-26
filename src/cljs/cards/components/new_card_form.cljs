(ns cards.components.new-card-form
  (:require [reagent.core :as r]
            [reagent-forms.core :refer [bind-fields]]
            [cards.components.forms :refer [form-group]]))

(defn- form-template []
  [:div
   (form-group :type "Type" {:input {:field :list :options {:twitter-user "Twitter user"}}})
   (form-group :value "User" {:input {:placeholder "@username"}})
   [:input.btn.btn-primary {:type "submit"}]])

(defn new-card-form [submit]
  (let [init {:type :twitter-user :value ""}
        data (r/atom init)]
    (fn []
      [:form.text-dark.text-left
       {:on-submit (fn [e]
                     (.preventDefault e)
                     (submit @data)
                     (reset! data init))}
       [bind-fields (form-template) data]])))
